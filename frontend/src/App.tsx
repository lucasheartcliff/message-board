import React from 'react';
import { useContext } from 'react';

import { ConfigProvider } from 'antd';
import locale from 'antd/lib/locale/pt_BR';
import { ThemeProvider } from 'styled-components';

import AuthProvider, { AuthContext } from './context/AuthContext';
import Router from './Router';
import themeSchema from './style/theme';

const theme = 'light';

const App: React.FC = () => {
  return (
    <ConfigProvider locale={locale}>
      <AuthProvider>
          <ThemeProvider theme={themeSchema[theme]}>
            <Router />
          </ThemeProvider>
      </AuthProvider>
    </ConfigProvider>
  );
};

export default App;
