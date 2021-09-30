import React, { FC, ReactElement, useState } from 'react';
import {
  CaretDownOutlined,
  UserOutlined,
} from '@ant-design/icons';
import { Button, Layout, Menu } from 'antd';
import { Container, Content, Header, UserContainer } from './styled';
import { paths } from '../../routes';
import { useHistory, useLocation } from 'react-router-dom';
import { useContext } from 'react';
import { AuthContext } from '../../context/AuthContext';

const { SubMenu } = Menu;
const { Sider } = Layout;

const UserTitle: React.FC<{ title: string }> = ({ title }) => {
  return (
    <UserContainer>
      <UserOutlined />
      {title}
      <CaretDownOutlined style={{ fontSize: 10 }} />
    </UserContainer>
  );
};
interface Props {
  children?: ReactElement;
}
const BaseLayout: FC<Props> = ({ children }) => {
  const { user, handleLogout } = useContext(AuthContext);
  const [collapsed, setCollapsed] = useState(false);
  const history = useHistory();
  const location = useLocation();

  const onSelectRoute = (path: string) => {
    history.push(path);
  };

  return (
    <Container>
      <Header>
        <Button type="text" onClick={() => onSelectRoute(paths.home)}>
          LOGO
        </Button>
        <Menu theme="light" mode="horizontal">
          <Menu.Item key="users" onClick={() => onSelectRoute(paths.userList)}>
            {'Users'}
          </Menu.Item>
          <SubMenu
            key="user"
            title={<UserTitle title={user?.email || ''} />}
            style={{ border: 0 }}
          >
            <Menu.Item key="logout" onClick={handleLogout}>
              {'Sair'}
            </Menu.Item>
          </SubMenu>
        </Menu>
      </Header>
      <Content>{children}</Content>
    </Container>
  );
};

export default BaseLayout;
