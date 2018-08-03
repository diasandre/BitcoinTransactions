import axios from "axios";

export function getData() {
  return axios.get('http://localhost:8080/endpoint/data');
}