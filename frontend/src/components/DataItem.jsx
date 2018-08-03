import React from "react";

class DataItem extends React.PureComponent{

    render(){
        return (
            <div className="data-item"> 
            <p className="title">{this.props.title}</p>
            <p>{this.props.data}</p>
            </div>
        )
    }
}

export default DataItem;