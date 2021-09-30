import { produce } from 'immer';
import React, { useCallback, useEffect, useState } from 'react';
import DeleteModal from '../../components/DeleteModal/DeleteModal';
import ListLayout from '../../components/ListLayout/ListLayout';
import { User } from '../../types/entities';
import { apiFetch, contains, notification } from '../../utils';
import { REMOVE_ENTITY } from '../../utils/message';
import { USER } from '../../utils/url';
import { columns } from './columns';

const ENTITY_NAME = 'Users';
interface Props {}
const Users: React.FC<Props> = () => {
  const [loading, setLoading] = useState(false);
  const [viewUserModal, setViewUserModal] = useState(false);
  const [viewDeleteModal, setViewDeleteModal] = useState(false);
  const [data, setData] = useState<User[]>();
  const [selectedUser, setSelectedUser] = useState<User>();
  const [selectedIndex, setSelectedIndex] = useState<number>();

  useEffect(() => {
    setLoading(true);
    apiFetch(USER())
      .get()
      .then(r => {
        if (!r.ok) throw r.statusText;
        return r.json();
      })
      .then(data => {
        setData(data);
      })
      .catch(err => {
        notification.error(err);
      })
      .finally(() => {
        setLoading(false);
      });
  }, []);

  const onFilter = (filterTerm: string, { email }: User, index: number) => {
    console.log(email, filterTerm, contains(email, filterTerm));
    return contains(email, filterTerm);
  };

  const onClickToCreate = useCallback(() => {
    setViewUserModal(true);
  }, []);

  const onClickToEdit = useCallback((row: User, index) => {
    setSelectedUser(row);
    setSelectedIndex(index);
    setViewUserModal(true);
  }, []);

  const onClickToRemove = useCallback((row: User, index) => {
    setSelectedUser(row);
    setSelectedIndex(index);
    setViewDeleteModal(true);
  }, []);

  const hideDeleteModal = useCallback(() => {
    setSelectedUser(undefined);
    setSelectedIndex(undefined);
    setViewDeleteModal(false);
  }, []);

  return (
    <>
      <ListLayout
        columns={columns}
        data={data || []}
        onClickBasicFilterButton={onFilter}
        entityName={ENTITY_NAME}
        loading={loading}
        onClickToCreate={onClickToCreate}
        onClickRow={onClickToEdit}
        // onClickToDelete={onClickToRemove}
      />
      {viewDeleteModal && selectedUser && selectedIndex !== undefined && (
        <DeleteModal
          hideModal={hideDeleteModal}
          entityId={selectedUser.id}
          visible={viewDeleteModal}
          entityName={ENTITY_NAME}
          urlBuilder={USER}
          onRemove={promise => {
            promise
              .then(() =>
                setData(
                  produce(state => {
                    delete state[selectedIndex];
                  })
                )
              )
              .then(() => {
                hideDeleteModal();
                notification.success(REMOVE_ENTITY);
              })
              .catch((error: any) => {
                notification.error(error.message);
              });
          }}
        />
      )}
    </>
  );
};

export default Users;
