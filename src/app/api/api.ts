import axios from 'axios';

const api = axios.create({
  baseURL: 'http://localhost:8080',
  headers: {
    'Content-Type': 'application/json',
  },
});

export const loginClient = (email: string, password: string) =>
  api.get(`/client/${encodeURIComponent(email)}/${encodeURIComponent(password)}`);

export default api;