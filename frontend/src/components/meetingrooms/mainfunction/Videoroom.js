import React, { useState, useEffect }  from 'react';
import './Videoroom.css';
import { Grid } from '@mui/material'

export default function Videoroom() {
  return (
    <Grid container id='participants' className="video-box"></Grid>
  );
};
