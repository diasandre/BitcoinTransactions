import React from 'react';
import { Line } from 'react-chartjs-2';
import { getChartData, getChartDataDefault } from "./../service/dataService"

import { formatDate } from './../service/formatService'

class Chart extends React.Component {
    constructor() {
        super();
        this.state = {
            dates: {
                fromDate: null,
                toDate: null
            },
            data: {
                labels: null,
                datasets: [
                    {
                        label: null,
                        data: []
                    }
                ]
            }
        }
    }

    componentDidMount() {
        this.setState({ dates: this.props.dates });

        getChartDataDefault(this.props.tradeType).then(response => {
            let chartData = response.data;

            chartData.labels = formatDate(chartData.labels);

            return chartData;
        }).then(content => {
            this.setState({ data: content })
        })
    }

    componentDidUpdate() {
        const { tradeType, dates } = this.props;
        const { fromDate, toDate } = this.state.dates;

        if (dates.fromDate !== fromDate || dates.toDate !== toDate) {
            if (dates.update) {
                getChartData(dates.fromDate, dates.toDate, tradeType).then(response => {
                    let chartData = response.data;

                    chartData.labels = formatDate(chartData.labels);

                    return chartData;
                }).then(content => {
                    this.setState({
                        data: content,
                        dates: dates,
                    })
                });
            }
        }
    }

    render() {
        return (
            <div>
                {
                    this.state.data != null ?
                        <Line
                            data={this.state.data}
                            height={300}
                            options={{
                                maintainAspectRatio: false,
                                elements: { point: { radius: 0 } }
                            }}
                        /> : <p> No data </p>
                }
            </div>
        )
    }

}

export default Chart;