import { useState } from 'react';
import {
  Grid,
  Button,
  Modal,
  TextField,
  Card,
} from '@mui/material'
import { createTeam } from '../../util/APIUtils';
import { ModalStyle } from './ModalStyle';

function CreateTeam(props) {
  const [open, setOpen] = useState(false);
  function handleOpen() {setOpen(true)};
  function handleClose() {setOpen(false)};
  const [teamName, setTeamName] = useState('');

  function handleChange({ target: {value} }) {setTeamName(value)};

  function handleSubmit(event) {
    event.preventDefault();
    createTeam({teamName: teamName});
    setTeamName('');
    setOpen(false);
    props.setReload(true);
  };
  
  function handelKeyPress(event) {
    if (event.key === "Enter") {
      handleSubmit();
    }
  };

  return (
    <Grid item>
      <Button onClick={handleOpen} variant="contained" sx={{m:1}}>팀 생성</Button>
      <Modal
        open={open}
        onClose={handleClose}
      >
        <Card
          sx={ModalStyle()}
        >
          <Grid container direction="column" rowSpacing={2}>
            <Grid item>팀 생성</Grid>
            <Grid item container
              component="form"
              onSubmit={handleSubmit}
              onKeyUp={handelKeyPress}
              spacing={2}
              justifyContent="center"
              alignItems="center"
              >
              <Grid item>
                <TextField
                  label="팀 이름"
                  name="teamName"
                  value={teamName}
                  size="small"
                  variant="outlined"
                  onChange={handleChange}
                />
              </Grid>
              <Grid item>
                <Button
                  type="submit"
                  variant="contained"
                >
                  생성
                </Button>
              </Grid>
            </Grid>
          </Grid>
        </Card>
      </Modal>
    </Grid>
  );
};

export default CreateTeam;