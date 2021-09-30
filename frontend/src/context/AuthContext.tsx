import React, { useEffect, useState } from 'react';
import { User } from '../types/entities';
import { apiFetch } from '../utils';
import { LOGIN, USER } from '../utils/url';

export interface LoginPayload {
  email: string;
}
interface AuthContext {
  user: User | null;
  loading: boolean;
  isAuthenticated: boolean;
  handleLogin: (payload: LoginPayload) => Promise<Boolean>;
  handleLogout: () => void;
}

const defaultConxtext: AuthContext = {
  user: null,
  loading: false,
  isAuthenticated: false,
  handleLogin: () => Promise.reject(false),
  handleLogout: () => {},
};

export const AuthContext = React.createContext<AuthContext>(defaultConxtext);

const AuthProvider = ({ children }: { children: React.ReactNode }) => {
  const [loading, setLoading] = useState<boolean>(false);
  const [user, setUser] = useState<User | null>(null);
  const [isAuthenticated, setAuthenticated] = useState(false);

  useEffect(() => {
    const userId = localStorage.getItem('userId');
    console.log('userId', userId);
    if (userId) {
      apiFetch(USER(userId))
        .get()
        .then(res => {
          if (!res.ok) throw res.statusText;
          return res.json();
        })
        .then(data => {
          setUser(data);
          setAuthenticated(true);
          return true;
        })
        .finally(() => {
          setLoading(false);
        });
    }
  }, []);

  const handleLogin = (payload: LoginPayload) => {
    setLoading(true);
    return apiFetch(LOGIN)
      .post(payload)
      .then(res => {
        return res.json();
      })
      .then(data => {
        setUser(data);
        setAuthenticated(true);
        console.log('data', data);
        localStorage.setItem('userId', String(data.id)); //This is not a good practice, but there's no restrictions in application
        return true;
      })
      .finally(() => {
        setLoading(false);
      });
  };

  const handleLogout = () => {
    try {
      setLoading(true);
      setUser(null);
      setAuthenticated(false);
      localStorage.setItem('userId', '');
    } finally {
      setLoading(false);
    }
  };

  return (
    <AuthContext.Provider
      value={{
        user,
        loading,
        isAuthenticated,
        handleLogin,
        handleLogout,
      }}
    >
      {children}
    </AuthContext.Provider>
  );
};

export default AuthProvider;
