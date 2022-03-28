import { Grid } from '@mui/material';

export default function Info(props) {

  return (
    <Grid className='kkwak' item container xs={2}>
      <Grid item xs={12}>
        <div className="sticky-info profile-container">
          <div className="container">
            <div className="profile-info">
              <div className="profile-avatar">
                { props.user.userImage ? (
                  <div className="">
                    <img src={props.user.userImage} alt="" />
                  </div>
                ) : (
                  <div className="text-avatar">
                    <span>Avatar</span>
                  </div>
                )
                }
                {/* <h1>{props.user.userName}</h1> */}
                <Grid item xs={12}>
                  <h5 className="info-text1">name : {props.user.userName}</h5>
                  <h5 className="info-text2">email : {props.user.userEmail}</h5>
                </Grid>
              </div>
            </div>
          </div>
        </div>
      </Grid>
    </Grid>
  )
}