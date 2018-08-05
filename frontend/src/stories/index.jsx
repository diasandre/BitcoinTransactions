import React from 'react';
import { storiesOf } from '@storybook/react';
import LargestTrades from './../components/LargestTrades';
import DataItem from './../components/DataItem';
import './../index.css';

storiesOf('Components', module)
  .add('LargestTrades', () =>
    <LargestTrades
      title="TITLE"
      data={[
        { "date": 1501889043, "amount": 3.99571885, "price": 9657, "type": "buy", "tid": 740624 },
        { "date": 1501888958, "amount": 2.57435, "price": 9656.9, "type": "buy", "tid": 740620 },
        { "date": 1501889180, "amount": 1.54929286, "price": 9656, "type": "buy", "tid": 740628 },
        { "date": 1501879302, "amount": 1.34596961, "price": 9575, "type": "buy", "tid": 740207 },
        { "date": 1501872927, "amount": 1, "price": 9700, "type": "buy", "tid": 739838 }]} />
  ).add('DataItem', () =>
    <DataItem title="TITLE" data={50}/>
  );