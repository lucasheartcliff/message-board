
export interface User {
  id: number;
  email: string;
}

export interface Message {
  id: number;
  message: string;
  user: User
  createdAt: string
  updatedAt: string
}