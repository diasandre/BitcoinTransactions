import React from 'react';
import { Line } from 'react-chartjs-2';
import { getChartData } from "./../service/dataService"
import moment from 'moment';

class Chart extends React.Component {
    constructor() {
        super();
        this.state = {
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
        getChartData(this.props.tradeType).then(response => {
            let chartData = response.data;

            chartData.labels = formatDate(chartData.labels);

            return chartData;
        }).then(content => {
            this.setState({data: content})
        })
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
                /> : "oi"
                }
            </div>
        )
    }

}

function formatDate(dates) {
    return dates.map(date => {
        return moment.unix(date).format('HH:mm')
    })
}

export default Chart;