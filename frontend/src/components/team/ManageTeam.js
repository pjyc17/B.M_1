import {
  Button,
  Card,
  Autocomplete,
  TextField,
  Grid,
  CardHeader,
  CardContent,
  CardActions,
  TableContainer,
  Table,
  TableRow,
  TableCell,
  TableHead,
  TableBody,
  TablePagination
} from '@mui/material'
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faUserMinus, faStar } from "@fortawesome/free-solid-svg-icons";
import { useEffect, useState } from 'react';
import { useLocation, useNavigate } from 'react-router-dom';
import { getOneTeam, inviteTeamMember, deleteTeam, deleteTeamMember, updateTeamName } from '../../util/APIUtils';

export function ManageTeam() {
  const navgate = useNavigate()
  const { state } = useLocation()
  const navigate = useNavigate()
  const teamId = state.teamId
  const teamLeaderId = state.teamLeaderId
  const [teamName, setTeamName] = useState(state.teamName)
  const users = state.users;
  const [currentTeam, setCurrentTeam] = useState([]);
  const [currentTeamId, setCurrentTeamId] = useState([]);
  const [currentTeamList, setCurrentTeamList] = useState([]);
  const [member, setMemeber] = useState({});
  const [submitMember, setSubmitMember] = useState(false);
  const [deleteMember, setDeleteMember] = useState(false);
  const [isDeleted, setIsDeleted] = useState(false);
  const [page, setPage] = useState(0);
  const [rowsPerPage, setRowsPerPage] = useState(5);
  
  // 팀 이름 변경
  function handleChangeTeamName({ target: {value} }) {setTeamName(value)};
  
  // 팀 이름 수정
  function handleSubmitTeamName(event) {
    event.preventDefault()
    updateTeamName(teamId, {teamName:teamName})
  };
  
  // 팀 멤버 변경
  function handleChangeMember(event, value) {setMemeber(value)};
  
  // 팀 멤버 초대
  function handleSubmitMember(event) {
    event.preventDefault()
    inviteTeamMember({team: teamId, user: member.id})
    setSubmitMember(true)
  };
  
  // 팀 삭제
  function handleDeleteTeam() {
    deleteTeam(teamId)
    setIsDeleted(true)
  };

  // 뒤로 가기
  function handleGoback() {navgate(-1)};
  
  // pagination 이동
  function handleChangePage(event, newPage) {
    console.log(newPage)
    setPage(newPage);
  };

  // pagination 범위 지정
  function handleChangeRowsPerPage(event) {
    setRowsPerPage(+event.target.value);
    setPage(0);
  };

  useEffect(() => {
    if (isDeleted) {
      setIsDeleted(false)
      navigate(-2)
    }
  }, [isDeleted, navigate])
  
  useEffect(() => {
    getOneTeam(teamId)
    .then(response => {
      setCurrentTeam(response.map(member => member.user))
      setSubmitMember(false)
      setDeleteMember(false)
    }).catch(error => {
      console.log(error)
    })
  }, [submitMember, deleteMember, teamId])
  
  useEffect(() => {
    if (currentTeam)
    setCurrentTeamId(currentTeam.map(member => member.id))
      setCurrentTeamList(currentTeam.slice(page * rowsPerPage, page * rowsPerPage + rowsPerPage).map(member => {
        return (
          <TableRow
            key={member.id}
            tabIndex={-1}
            >          
            <TableCell>{ member.name}</TableCell>
            <TableCell>{ member.email }</TableCell>            
            <TableCell align="center">
              { member.id !== teamLeaderId ?
                  <Button
                  id={member.id}
                    onClick={(event) => {
                      deleteTeamMember(teamId, event.target.id)
                      setDeleteMember(true)
                    }}
                  >
                    <FontAwesomeIcon  icon={faUserMinus}/>
                  </Button>
                : <FontAwesomeIcon icon={faStar} color="#ffee58"/>}
            </TableCell>
          </TableRow>
        )
      }))
    }, [currentTeam, page, rowsPerPage, teamId, teamLeaderId])
    
  return (
    <Card
      sx={{
        mt: '5rem',
        mx: 'auto',
        width: '30rem'
      }}
    >
      <CardHeader
        title='팀 관리'
      />
      <CardContent>
        <Grid
          container
          alignItems="center"
        >
          <Grid item
            container
            spacing={2}
            justifyContent="space-between"
            alignItems="center"
            component="form"
            onSubmit={handleSubmitTeamName}
          >
            <Grid item>
              <TextField
                label="팀 이름"
                size="small"
                sx={{
                  width:"22rem"
                }}
                defaultValue={teamName}
                onChange={handleChangeTeamName}
              />
            </Grid>
            <Grid item>
              <Button
                type="submit"
                variant="contained"
              >
                수정
              </Button>
            </Grid>
          </Grid>
        </Grid>
      </CardContent>
      <CardContent>
        <Grid
          container
          spacing={2}
          justifyContent="space-between"
          alignItems="center"
          component="form"
          onSubmit={handleSubmitMember}
        >
          <Grid item xs={9}>
            <Autocomplete
              options={users}
              size="small"
              sx={{
                width:"22rem"
              }}
              getOptionLabel={(option) => option.email}
              getOptionDisabled={(option) =>
                currentTeamId.some(value => value===option.id)
              }
              renderInput={(params) => (
                <TextField
                {...params}
                label="팀원"
                />
                )}
              onChange={handleChangeMember}
            />
          </Grid>
          <Grid item>
            <Button
              type="submit"
              variant="contained"
            >
              추가
            </Button>
          </Grid>
        </Grid>
      </CardContent>
        <TableContainer>
          <Table>
            <TableHead>
              <TableRow>
                <TableCell>이름</TableCell>
                <TableCell>e-mail</TableCell>
                <TableCell></TableCell>
              </TableRow>
            </TableHead>
            <TableBody>
              {currentTeamList}
            </TableBody>
          </Table>
        </TableContainer>
        <TablePagination
          rowsPerPageOptions={[5, 10, 20]}
          component="div"
          count={currentTeam.length}
          rowsPerPage={rowsPerPage}
          page={page}
          onPageChange={handleChangePage}
          onRowsPerPageChange={handleChangeRowsPerPage}
        />
      <CardActions>
        <Grid container
          justifyContent="space-between"
        >
          <Button
            variant="contained"
            onClick={handleGoback}
          >
            뒤로가기
          </Button>
          <Button
            variant="contained"
            color= 'warning'
            onClick={handleDeleteTeam}
          >
            팀 삭제
          </Button>
        </Grid>
      </CardActions>
    </Card>
  );
};

export default ManageTeam;