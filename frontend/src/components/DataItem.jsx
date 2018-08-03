import React from "react";

class DataItem extends React.PureComponent{

    render(){
        return (
            <div className="data-item"> 
                {this.props.title + " - " + this.props.data}
            </div>
        )
    }
}

export default DataItem;