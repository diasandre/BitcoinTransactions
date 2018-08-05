import React from 'react';
import logo from './image/bitcoin.png';
import { getData } from "./service/dataService"
import Trade from './components/Trade';
import './index.css';

class App extends React.Component {
  constructor() {
    super();
    this.state = {
      data: null,
      error: null
    }
  }

  componentDidMount() {
    getData().then(response => {
      this.setState({ data: response.data });
    }).catch(error => {
      this.setState({ error: "Erro de conexão com o backend" });
    });
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
              <Trade tradeType="buy" largest={this.state.data.largest_buy}
                average={this.state.data.average_buy}
                median={this.state.data.median_buy}
                deviation={this.state.data.deviation_buy} />

              <Trade tradeType="sell" largest={this.state.data.largest_sell}
                average={this.state.data.average_sell}
                median={this.state.data.median_sell}
                deviation={this.state.data.deviation_sell} />
            </div>) :
            <p className="error">{this.state.error}</p>
          }
        </div>
      </div>
    );
  }

}

export default App;
