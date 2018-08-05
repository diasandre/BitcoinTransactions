import axios from "axios";
import {isLocalhost} from "./../registerServiceWorker"

const URL_BASE = isLocalhost ?
  "http://localhost:8080/endpoint/" :
  "http://produrl.com.br"

export function getData() {
  return axios.get(URL_BASE + 'data');
}

export function getChartData(tradeType) {
  return axios.get(URL_BASE + 'chart/' + tradeType)
}