import React from 'react';

class LargestTrades extends React.PureComponent {
    render() {
        return (
            <div>
                <p className="section-title">
                    {this.props.title}
                </p>
                {
                    this.props.data.map(
                        item => createTradeItem(item))
                }
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
            <p>
                Price: {item.price}
            </p>
            <p>
                Amount: {item.amount}
            </p>
        </div>
    )
}

export default LargestTrades