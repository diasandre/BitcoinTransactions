import moment from 'moment'

export function toUnix(value){
    return moment(value).format('X');
}

export function toDatePicker(value){
    return moment.unix(value).format('MM-DD-YY');
}

export function formatDate(dates) {
    return dates.map(date => {
        return moment.unix(date).format('DD/MM - HH:mm')
    })
}