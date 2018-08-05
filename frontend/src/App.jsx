import React from 'react';
import 'react-awesome-button/dist/themes/theme-c137.css';
import './css/index.css';
import './css/react-date-picker.css';

import logo from './image/bitcoin.png';
import { getData, getDataDefault, } from "./service/dataService"
import Trade from './components/Trade';
import Datepicker from 'react-basic-datepicker';

import { toUnix, toDatePicker } from './service/formatService'

import {
  AwesomeButton
} from 'react-awesome-button';

import numeral from 'numeral';

class App extends React.Component {
  constructor() {
    super();
    this.state = {
      data: null,
      error: null,
      dates: {
        fromDate: 1501871369,
        toDate: 1501891200,
        update: false
      }
    }
  }

  componentDidMount() {
    getDataDefault().then(response => {
      this.setState({ data: response.data });
    }).catch(error => {
      this.setState({ error: "Erro de conexão com o backend" });
    });
  }

  fromDateChange(date) {
    this.setState({
      dates: {
        ...this.state.dates,
        update: false,
        fromDate: toUnix(date),
      }
    })
  }

  toDateChange(date) {
    this.setState({
      dates: {
        ...this.state.dates,
        update: false,
        toDate: toUnix(date)
      }
    })
  }

  onClick() {
    const { dates } = this.state;

    if (numeral(dates.toDate) >= numeral(dates.fromDate) || dates.fromDate === dates.toDate) {
      this.setState({ error: "As datas estão inválidas" });
    } else {
      getData(dates.fromDate, dates.toDate).then(response => {
        this.setState({
          data: response.data,
          dates: {
            ...this.state.dates,
            update: true
          }
        });
      }).catch(error => {
        this.setState({ error: "Erro de conexão com o backend" });
      });
    }
  }

  render() {
    return (
      <div className="App">
        <header className="header">
          <img src={logo} className="logo" alt="logo" />
        </header>
        <div>
          {this.state.data != null ?
            (<div className="container">
              <div className="date-picker">
                <div className="item">
                  <h2>From:</h2>
                  <Datepicker
                    startDate={new Date(toDatePicker(this.state.dates.fromDate))}
                    minDate={new Date(2009, 12)}
                    handleDateChange={this.fromDateChange.bind(this)}
                    dateFormat="DD/MM/YYYY" />
                </div>
                <div className="search">
                  <AwesomeButton
                    type="primary"
                    size="medium"
                    action={this.onClick.bind(this)}>
                    Search
                    </AwesomeButton>
                </div>
                <div className="item">
                  <h2>To:</h2>
                  <Datepicker
                    startDate={new Date(toDatePicker(this.state.dates.toDate))}
                    minDate={new Date(2009, 12)}
                    handleDateChange={this.toDateChange.bind(this)}
                    dateFormat="DD/MM/YYYY" />
                </div>
              </div>

              <p className="error">{this.state.error}</p>

              <Trade tradeType="buy" largest={this.state.data.largest_buy}
                average={this.state.data.average_buy}
                median={this.state.data.median_buy}
                deviation={this.state.data.deviation_buy}
                dates={this.state.dates} />

              <Trade tradeType="sell" largest={this.state.data.largest_sell}
                average={this.state.data.average_sell}
                median={this.state.data.median_sell}
                deviation={this.state.data.deviation_sell}
                dates={this.state.dates} />
            </div>) :
            <p className="error">{this.state.error}</p>
          }
        </div>
      </div>
    );
  }

}

export default App;
