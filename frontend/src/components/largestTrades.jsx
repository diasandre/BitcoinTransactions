import React from 'react';
import moment from 'moment';
import numeral from 'numeral';

class LargestTrades extends React.PureComponent {
    render() {
        return (
            <div>
                <p className="section-title">
                    {this.props.title}
                </p>
                <div className="largest" >
                    {
                        this.props.data.map(
                            item => createTradeItem(item))
                    }
                </div>
            </div>
        )
    }
}

function createTradeItem(item) {
    return (
        <div className="item" key={item.tid}>
            <p className="tid">
                {item.tid}
            </p>
            <p className="date">
                {moment.unix(item.date).format('DD/MM/YYYY - HH:mm')}
            </p>
            <hr/>
            <p>
                Price: {numeral(item.price).format('$0.0,0')}
            </p>
            <p>
                Amount: {item.amount}
            </p>
        </div>
    )
}

export default LargestTrades