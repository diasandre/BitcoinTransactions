import axios from "axios";
import {isLocalhost} from "./registerServiceWorker"

const URL_BASE = isLocalhost ?
  "http://localhost:8080/endpoint/" :
  "http://PROD.com.br"

export function getDataDefault() {
  return axios.get(URL_BASE + 'data');
}

export function getData(fromDate, toDate) {
  return axios.get(URL_BASE + "data/" + fromDate + "/" + toDate );
}

export function getChartDataDefault(tradeType) {
  return axios.get(URL_BASE + 'chart/' + tradeType)
}

export function getChartData(fromDate, toDate, tradeType) {
  return axios.get(URL_BASE + "chart/" + fromDate + "/" + toDate + "/" + tradeType);
}