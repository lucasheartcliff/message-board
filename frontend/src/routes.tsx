import React, { useContext } from 'react';
import { Redirect, Route, RouteProps, Switch } from 'react-router-dom';

import { AuthContext } from './context/AuthContext';
import Home from './pages/Home/Home';
import Login from './pages/Login/Login';
import Users from './pages/Users/Users';

export const paths = {
  home: '/',
  userList: '/user',
  login: '/login',
};

const PrivateRoute = ({ ...rest }: RouteProps) => {
  const { isAuthenticated } = useContext(AuthContext);

  return isAuthenticated ? (
    <Route {...rest} />
  ) : (
    <Redirect to={{ pathname: paths.login }} />
  );
};

const Routes: React.FC = () => {
  return (
    <Switch>
      <PrivateRoute path={paths.home} exact component={Home} />
      <PrivateRoute path={paths.userList} exact component={Users} />
      <Route exact path="*" render={() => <Redirect to={paths.home} />} />
    </Switch>
  );
};

export const LoginRoute = () => {
  return (
    <Switch>
      <Route exact path={paths.login} component={Login} />
      <Route exact path="*" render={() => <Redirect to={paths.login} />} />
    </Switch>
  );
};

export default Routes;
