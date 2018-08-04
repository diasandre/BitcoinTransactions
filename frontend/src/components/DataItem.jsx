import React from "react";
import numeral from 'numeral';

class DataItem extends React.PureComponent {

    render() {
        return (
            <div className="data-item">
                <p className="title">{this.props.title}</p>
                <p>
                    {numeral(this.props.data).format('0.000')}
                </p>
            </div>
        )
    }
}

export default DataItem;