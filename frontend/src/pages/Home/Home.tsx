import { List, Tooltip, Comment, PageHeader } from 'antd';
import produce from 'immer';
import moment from 'moment';
import React, { useContext, useEffect, useRef, useState } from 'react';
import Scrollbars from 'react-custom-scrollbars';
import Editor from '../../components/Editor/Editor';
import Scroll from '../../components/Scroll/Scroll';
import { AuthContext } from '../../context/AuthContext';
import { Container } from '../../style/global';
import { Message } from '../../types/entities';
import { apiFetch, notification } from '../../utils';
import { MESSAGE } from '../../utils/url';

import * as S from './styled';

const Home = () => {
  const { user } = useContext(AuthContext);
  const [messages, setMessages] = useState<Message[]>([]);
  const [loading, setLoading] = useState(false);
  const [editorValue, setEditorValue] = useState<string>();
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
      console.log(ref);
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
      console.log(payload, user);
      setLoading(true);
      apiFetch(MESSAGE())
        .post(payload)
        .then(r => {
          if (!r.ok) throw r.statusText;
          return r.json();
        })
        .then(data => {
          setMessages([...messages, data]);
        })
        .catch(err => {
          notification.error(err);
        })
        .finally(() => {
          setLoading(false);
        });
    }
  };

  return (
    <Container style={{ flexDirection: 'column' }}>
      <S.ChatContainer>
        <PageHeader title="Open Chat" subTitle="Tell everything to everybody" />
        <Scroll ref={el => (ref.current = el)} height={'100%'}>
          <List
            dataSource={messages}
            itemLayout="horizontal"
            loading={loading}
            renderItem={({ user, message, id, createdAt, updatedAt }) => {
              const formattedDate = moment(createdAt).format(
                'YYYY-MM-DD HH:mm:ss'
              );

              return (
                <Comment
                  key={id}
                  author={
                    <span style={{ marginLeft: 10, fontWeight: 500 }}>
                      {user.email}
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
    </Container>
  );
};

export default Home;
