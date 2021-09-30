import { DeleteOutlined } from '@ant-design/icons';
import { List, Tooltip, Comment, PageHeader } from 'antd';
import produce from 'immer';
import moment from 'moment';
import React, { useContext, useEffect, useRef, useState } from 'react';
import Scrollbars from 'react-custom-scrollbars';
import DeleteModal from '../../components/DeleteModal/DeleteModal';
import Editor from '../../components/Editor/Editor';
import Scroll from '../../components/Scroll/Scroll';
import { AuthContext } from '../../context/AuthContext';
import { Container } from '../../style/global';
import { Message } from '../../types/entities';
import { apiFetch, notification, sortBy } from '../../utils';
import { REMOVE_CONFIRMATION, REMOVE_ENTITY } from '../../utils/message';
import { MESSAGE } from '../../utils/url';

import * as S from './styled';

type MessageMap = {
  [key: number]: Message;
};

const Home = () => {
  const { user } = useContext(AuthContext);
  const [messages, setMessages] = useState<MessageMap>({});
  const [loading, setLoading] = useState(false);
  const [editorValue, setEditorValue] = useState<string>();
  const [selectedMessage, setSelectedMessage] = useState<number>();
  const ref = useRef<Scrollbars | null>();

  useEffect(() => {
    setLoading(true);
    apiFetch(MESSAGE())
      .get()
      .then(r => {
        if (!r.ok) throw r.statusText;
        return r.json();
      })
      .then(data => {
        setMessages(data);
      })
      .catch(err => {
        notification.error(err);
      })
      .finally(() => {
        setLoading(false);
      });
  }, []);

  useEffect(() => {
    if (ref.current) {
      ref.current.scrollToBottom();
    }
  }, [messages, ref]);

  const onChangeEditor = (value?: string) => {
    setEditorValue(value);
  };

  const onSubmit = () => {
    if (user && editorValue) {
      const payload = {
        userId: user?.id,
        message: editorValue,
      };
      setLoading(true);
      apiFetch(MESSAGE())
        .post(payload)
        .then(r => {
          if (!r.ok) throw r.statusText;
          return r.json();
        })
        .then(data => {
          setMessages({ ...messages, [data.id]: data });
          setEditorValue('');
        })
        .catch(err => {
          notification.error(err);
        })
        .finally(() => {
          setLoading(false);
        });
    }
  };

  const onRemove = () => {
    if (selectedMessage) {
      setLoading(true);
      apiFetch(MESSAGE(selectedMessage))
        .delete()
        .then(r => {
          if (!r.ok) throw r.statusText;
        })
        .then(() => {
          const newMap = { ...messages };
          delete newMap[selectedMessage];
          setMessages(newMap as any);
          setSelectedMessage(undefined);
          notification.success(REMOVE_ENTITY);
        })
        .catch(err => {
          notification.error(err);
        })
        .finally(() => setLoading(false));
    }
  };

  return (
    <Container style={{ flexDirection: 'column' }}>
      <S.ChatContainer>
        <PageHeader title="Open Chat" subTitle="Tell everything to everybody" />
        <Scroll ref={el => (ref.current = el)} height={'100%'}>
          <List
            dataSource={Object.values(messages).sort((a, b) =>
              sortBy(a, b, 'createdAt')
            )}
            itemLayout="horizontal"
            loading={loading}
            renderItem={({
              user: author,
              message,
              id,
              createdAt,
              updatedAt,
            }) => {
              const formattedDate = moment(createdAt).format(
                'YYYY-MM-DD HH:mm:ss'
              );

              return (
                <Comment
                  key={id}
                  children={
                    user?.id === author.id ? (
                      <Tooltip title={'Delete'}>
                        <DeleteOutlined
                          onClick={() => setSelectedMessage(id)}
                          style={{
                            position: 'absolute',
                            top: 17.5,
                            right: 20,
                            cursor: 'pointer',
                          }}
                        />
                      </Tooltip>
                    ) : null
                  }
                  style={{ position: 'relative' }}
                  author={
                    <span style={{ marginLeft: 10, fontWeight: 500 }}>
                      {author.email}
                    </span>
                  }
                  content={<p style={{ padding: '10px 15px' }}>{message}</p>}
                  datetime={
                    <Tooltip title={formattedDate}>
                      <span>{formattedDate}</span>
                    </Tooltip>
                  }
                />
              );
            }}
          />
        </Scroll>
      </S.ChatContainer>
      <S.EditorContainer>
        <Editor
          onChange={onChangeEditor}
          value={editorValue}
          onSubmit={onSubmit}
          loading={loading}
        />
      </S.EditorContainer>

      {selectedMessage && (
        <DeleteModal
          entityName={'Message'}
          hideModal={() => setSelectedMessage(undefined)}
          onRemove={onRemove}
          visible={!!selectedMessage}
        />
      )}
    </Container>
  );
};

export default Home;
