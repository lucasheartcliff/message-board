import { Button } from 'antd';
import Modal from 'antd/lib/modal/Modal';
import React from 'react';
import { apiFetch } from '../../utils';
import { REMOVE_CONFIRMATION } from '../../utils/message';
import { Container, Message } from './styled';

interface Props<T> {
  entityName: string;
  entityId: number;
  urlBuilder: (id?: string | number) => string;
  visible: boolean;
  hideModal: () => void;
  onRemove: (promise: Promise<any>) => void;
}

const DeleteModal = <T extends any>({
  visible,
  hideModal,
  onRemove,
  urlBuilder,
  entityId,
  entityName,
}: Props<T>) => {
  // const [mutation] = useMutation(queryString)

  const onClickToRemove = () => {
    onRemove(apiFetch(urlBuilder(entityId)).delete());
  };

  return (
    <Modal
      visible={visible}
      width={500}
      title={`Remover ${entityName}`}
      onCancel={hideModal}
      footer={[
        <Button key="filter" type="primary" danger onClick={onClickToRemove}>
          {'Remover'}
        </Button>,
      ]}
    >
      <Container>
        <Message>{REMOVE_CONFIRMATION}</Message>
      </Container>
    </Modal>
  );
};

export default DeleteModal;
