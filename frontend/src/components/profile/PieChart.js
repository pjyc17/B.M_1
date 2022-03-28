import * as React from 'react';
import { useState, useEffect } from 'react';
// import { getCurrentUser, getAttenders } from '../../util/APIUtils';


import { PieChart } from 'react-minimal-pie-chart';
import Paper from '@mui/material/Paper';
import { Card, Grid } from '@mui/material';

export default function Piechart(props) {
  const hatTime = props.hatTime

  return (
    <Paper className='chart-color kkwak'>
    <PieChart
      data={[
        { title: 'Red', value: hatTime[2], color: '#ea5f64' },
        { title: 'Green', value: hatTime[3], color: '#78f99c'},
        { title: 'Blue', value: hatTime[4], color: '#7074bf' },
        { title: 'White', value: hatTime[5], color: '#EBEBEB' }, 
        { title: 'Black', value: hatTime[0], color: '#848484'}, 
        { title: 'Yellow', value: hatTime[1], color: '#fff88f'},

      ]}
    />
  </Paper>
  )
};