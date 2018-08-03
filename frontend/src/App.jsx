import React from 'react';
import logo from './image/bitcoin.png';
import './App.css';
import { getData } from "./service/dataService"

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
      <p className="section-title">
        LARGEST SELL TRADES
    </p>
      <div>
        {
          data.largest_sell.map(
            item => createTradeItem(item))
        }
      </div>
      <p className="section-title">
        LARGEST BUY TRADES
  </p>
      <div>
        {
          data.largest_buy.map(
            item => createTradeItem(item))
        }
      </div>
    </div>
  )
}


function createTradeItem(item) {
  return (
    <div className="item" key={item.tid}>
      <p className="tid">
        {item.tid}
      </p>
      <p>
        Price: {item.price}
      </p>
      <p>
        Amount: {item.amount}
      </p>
    </div>
  )
}

export default App;
