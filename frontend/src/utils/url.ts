const host = 'http://localhost:8080';

export const LOGIN = `${host}/auth/login`;
export const USER = (id?: string | number) =>
  `${host}/api/users${id ? `/${id}` : ''}`;

export const MESSAGE = (id?: string | number) =>
  `${host}/api/messages${id ? `/${id}` : ''}`;
