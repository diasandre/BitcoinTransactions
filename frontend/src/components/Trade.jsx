import React from "react";
import LargestTrades from "./largestTrades"
import DataItem from "./DataItem";

class Trade extends React.PureComponent {
    render() {
        return (
            <div>
                <h1 className="trade-name">{this.props.tradeType}</h1>
                <div>
                    {generateData(this.props)}
                </div>
            </div>
        )
    }
}

function generateData(props) {
    const {largest, average, median, deviation} = props;
    return (
        <div>
            <div>
                <LargestTrades
                    title="LARGEST TRADES"
                    data={largest} />
            </div>
            <div>
                <DataItem title="Average" data={average} />
                <DataItem title="Median" data={median} />
                <DataItem title="Standard Deviation" data={deviation} />
            </div>
        </div>
    )
}

export default Trade;