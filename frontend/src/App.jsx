import React from 'react';
import logo from './image/bitcoin.png';
import './App.css';
import { getData } from "./service/dataService"
import LargestTrades from './components/largestTrades';

class App extends React.Component {
  constructor() {
    super();
    this.state = {
      data: null
    }
  }

  componentDidMount() {
    getData().then(response => {
      this.setState({ data: response.data });
    });
  }

  render() {
    return (
      <div className="App">
        <header className="header">
          <img src={logo} className="logo" alt="logo" />
        </header>
        <div className="container">
          <div className="largest">
            {this.state.data != null ?
              generateData(this.state.data) :
              <p>Loading data....</p>}
          </div>
        </div>
      </div>
    );
  }
}

function generateData(data) {
  return (
    <div>
      <LargestTrades title="LARGEST SELL TRADES"
        data={data.largest_sell} />
      <LargestTrades title="LARGEST BUY TRADES"
        data={data.largest_buy} />
    </div>
  )
}

export default App;
