import { Button } from 'antd';
import Modal from 'antd/lib/modal/Modal';
import React from 'react';
import { REMOVE_CONFIRMATION } from '../../utils/message';
import { Container, Message } from './styled';

interface Props<T> {
  entityName: string;
  visible: boolean;
  hideModal: () => void;
  onRemove: () => void;
}

const DeleteModal = <T extends any>({
  visible,
  hideModal,
  onRemove,
  entityName,
}: Props<T>) => {
  return (
    <Modal
      visible={visible}
      width={500}
      title={`Remover ${entityName}`}
      onCancel={hideModal}
      footer={[
        <Button key="filter" type="primary" danger onClick={onRemove}>
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
