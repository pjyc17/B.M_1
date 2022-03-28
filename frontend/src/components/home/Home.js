import './Home.css';
import { Grid } from '@mui/material';
import styled from 'styled-components'

const Hidden = styled.div`
  display: block;
  width: 100%;
  height: 100px;
  @media only screen and (min-width: 900px) {
    display: none;
  }
`;

const HiddenWithHatIntro = styled.div`
  display: none;
  @media only screen and (min-width: 900px) {
    display: block;
    width: 100%;
    height: 100px;
  }
`

const MiddleLetter = styled.h2`
  font-size: 30px;
  @media only screen and (min-width: 600px) {
    font-size: 40px;
  }
  @media only screen and (min-width: 900px) {
    font-size: 50px
  }
`

const MainLogoImage = styled.img`
  width: 100%;
  @media only screen and (min-width: 600px) {
    width: 80%;
  }
  @media only screen and (min-width: 900px) {
    width: 50%;
  }
`

const Better = styled.h3`
  font-size: 20px;
  width: 50%;
  margin-left: 41.5%;
  @media only screen and (min-width: 600px) {
    font-size: 28px;
    width: 48%;
    margin-left: 43.5%;
  }
  @media only screen and (min-width: 900px) {
    font-size: 36px;
    width: 43%;
    margin-left: 48.5%;
  }
`

const MeetingImage = styled.img`
  width: 170%;
  height: auto;
  
  @media only screen and (min-width: 1300px) {
    width: 100%;
    height: auto;
  }
`

const Title = styled.div`
  font-size: 28px;
  @media only screen and (min-width: 1300px) {
    font-size: 44px;
  }
  @media only screen and (max-width: 900px) {
    font-size: 40px;
  }
`

const Content = styled.div`
  font-size: 16px;
  @media only screen and (min-width: 1300px) {
    font-size: 24px;
  }
  @media only screen and (max-width: 900px) {
    font-size: 20px;
  }
`

const ContentHat = styled.div`
  font-size: 14px;
  @media only screen and (min-width: 1300px) {
    font-size: 22px;
  }
  @media only screen and (max-width: 900px) {
    font-size: 20px;
  }
`
const HatIntro = styled.div`
  font-size: 18px;
  

  @media only screen and (min-width: 1300px) {
    font-size: 22px;
  }
  @media only screen and (max-width: 900px) {
    font-size: 16px;
  }
  @media only screen and (max-width: 800px) {
    font-size: 14px;
  }
`

export function Home () {
  return (
  <div>
    <Grid>
      <MiddleLetter className='middleLetter'>지금부터 회의를 시작합니다.</MiddleLetter>
    </Grid>
    <Grid className="mainLogoGrid">
      <MainLogoImage className="mainLogoImage" src={require("./img/검정로고2.png")} alt="logoimage"></MainLogoImage>
    </Grid>
    <Grid>
      <hr className='logoLine'></hr>
    </Grid>
    <Grid>
      <Better className='better'>Better than meetings</Better>
    </Grid>
    
    <Grid className='introBox' container>
      <Grid item sm={12} md={1}/>
      <Grid item sm={12} md={5}>
        <MeetingImage className='meetingImage' src={require('./img/회의일러스트.png')} alt="meeting"></MeetingImage>
      </Grid>
      <Grid className='introLetter' item sm={12} md={5}>
        <br></br>
        <Title className='title'>
          회의가 성공으로 가는 지름길
        </Title>
        <br></br>
        <br></br>
        <br></br>
        <br></br>
        <Content className='content'>
          여기는 회의가 서툰 사람들이 좀 더 효율적인 아이디어를
        </Content>
        <Content className='content'>
          발상하기를 원하는 여러분들을 위한 곳 입니다.
        </Content>
        <br></br>
        <br></br>
        <Content className='content'>
          우리가 제공하는 회의 기능을 사용해서 당신의 창의적인
        </Content>
        <Content className='content'>
          생각과 참신한 의견을 맘껏 펼쳐 보세요.
        </Content>
        <br></br>
        <br></br>
        <Content className='content'>
          여러분들이 가지고 있는 많은 생각들을 Beyond Meeting를 통해서
        </Content>
        <Content className='content'>
          쉽고 간편하게 밖으로 이끌어 보세요.
        </Content>
      </Grid>
      <Grid item xs={12} sm={1} />
    </Grid>
    
    <Grid className='introSixHatBox' container>
      <Grid item sm={12} md={1} />
      <Grid className='introSixHat' item sm={12} md={11}>
        <Title className='title'>
          여섯 색깔 모자 기법
        </Title>
        <br></br>
        <br></br>
        <br></br>
        <ContentHat className='contentHat'>
          창의적 사고와 사고를 기술로 직접 가르치는 분야의 권위자인
        </ContentHat>
        <ContentHat className='contentHat'>
        <span style={{ color: 'rgb(102, 103, 171)' }}>에드워드 드 보노(Edward de Bono)박사</span>가 제안한 창의적 사고를 위한 회의 기법입니다.
        </ContentHat>
        <br></br>
        <br></br>
        <ContentHat className='contentHat'>
          이 기법은 세대, 대륙 및 신념 체계를 아우르고 있으며, Apple 및 British Airways와 같은 주요 기업의 이사회에서,
        </ContentHat>
        <ContentHat className='contentHat'>
          아프리카 시골의 강의실에서도 똑같이 영향력을 행사합니다.
        </ContentHat>
        <br></br>
        <br></br>
        <ContentHat className='contentHat'>
          우리는 결정을 내릴 때 장단점을 생각하고 이에 따른 감정을 느낍니다.
        </ContentHat>
        <ContentHat className='contentHat'>
          동시에 다가오는 감정, 생각을 나누어 다른 사람과 공유하며 생각의 복잡성을 버리고 생각이 명확하고
        </ContentHat>
        <ContentHat className='contentHat'>
         단순해진다면 빠르고 쉽게 결론에 다다를 수 있습니다.
        </ContentHat>
      </Grid>
      <HiddenWithHatIntro></HiddenWithHatIntro>
      <Grid item sm={12} md={3}>
        <Hidden></Hidden>
      </Grid>
      <Grid container item sm={12} md={9}>
        <Grid container item xs={6}>
          <Grid item xs={4} className='hatBox'>
            <img className='redHat' src={require('./img/723.png')} alt='cap'></img>
          </Grid>
          <Grid className='hatBox' item xs={8}>
            <HatIntro className='hatIntro'>
              - 당신은 <span style={{ color: 'rgb(240, 60, 60)', fontWeight: 'bold' }}>직관주의자</span>입니다.
            </HatIntro>
            <HatIntro>
              - 방금 떠오르는 생각을 
            </HatIntro>
            <HatIntro className='hatIntro'>
              &nbsp; 가감없이 말하세요.
            </HatIntro>
            <HatIntro>
              - 논리적일 필요도 없습니다.
            </HatIntro>
          </Grid>
        </Grid>
        <Grid container item xs={6}>
          <Grid item xs={4} className='hatBox'>
            <img className='blueHat' src={require('./img/723.png')} alt='cap'></img>
          </Grid>
          <Grid className='hatBox' item xs={8}>
            <HatIntro className='hatIntro'>
              - 당신은 <span style={{ color: 'rgb(65, 65, 220)', fontWeight: 'bold' }}>사회자</span>입니다.
            </HatIntro>
            <HatIntro>
              - 다른 사람의 이야기를 듣고
            </HatIntro>
            <HatIntro className='hatIntro'>
              &nbsp; 회의를 잘 조직해 보세요.
            </HatIntro>
            <HatIntro>
              - 결론을 이끌어 내세요.
            </HatIntro>
          </Grid>
        </Grid>
        <Grid container item xs={6}>
          <Grid item xs={4} className='hatBox'>
            <img className='greenHat' src={require('./img/723.png')} alt='cap'></img>
          </Grid>
          <Grid className='hatBox' item xs={8}>
            <HatIntro className='hatIntro'>
              - 당신은 <span style={{ color: 'rgb(146, 208, 80)', fontWeight: 'bold' }}>아이디어뱅크</span>입니다.
            </HatIntro>
            <HatIntro>
              - 창의성과 새로운 아이디어를
            </HatIntro>
            <HatIntro className='hatIntro'>
              &nbsp; 제시하세요.
            </HatIntro>
            <HatIntro>
              - 조금 터무니없어도 좋아요.
            </HatIntro>
          </Grid>
        </Grid>
        <Grid container item xs={6}>
          <Grid item xs={4} className='hatBox'>
            <img className='whiteHat' src={require('./img/723.png')} alt='cap'></img>
          </Grid>
          <Grid className='hatBox' item xs={8}>
            <HatIntro className='hatIntro'>
              - 당신은 <span style={{ textDecoration: 'underline', fontWeight: 'bold' }}>분석가</span>입니다.
            </HatIntro>
            <HatIntro>
              - 항상 중립적으로 떨어져서
            </HatIntro>
            <HatIntro className='hatIntro'>
              &nbsp; 회의를 지켜보세요.
            </HatIntro>
            <HatIntro>
              - 객관적인 사실만 말하세요.
            </HatIntro>
          </Grid>
        </Grid>
        <Grid container item xs={6}>
          <Grid item xs={4} className='hatBox'>
            <img className='blackHat' src={require('./img/723.png')} alt='cap'></img>
          </Grid>
          <Grid className='hatBox' item xs={8}>
            <HatIntro className='hatIntro'>
              - 당신은 <span style={{ color: 'rgb(102, 103, 171)', fontWeight: 'bold'  }}>비평가</span>입니다.
            </HatIntro>
            <HatIntro>
              - 아이디어의 약점을 찾아보며
            </HatIntro>
            <HatIntro className='hatIntro'>
              &nbsp; 약점을 잡아내세요.
            </HatIntro>
            <HatIntro>
              - 비판적으로 지적하세요.
            </HatIntro>
          </Grid>
        </Grid>
        <Grid container item xs={6}>
          <Grid item xs={4} className='hatBox'>
            <img className='yellowHat' src={require('./img/723.png')} alt='cap'></img>
          </Grid>
          <Grid className='hatBox' item xs={8}>
            <HatIntro className='hatIntro'>
              - 당신은 <span style={{ color: 'yellow', fontWeight: 'bold'  }}>낙관주의자</span>입니다.
            </HatIntro>
            <HatIntro>
              - 항상 아이디어의 희망과
            </HatIntro>
            <HatIntro className='hatIntro'>
              &nbsp; 장점을 바라보세요.
            </HatIntro>
            <HatIntro>
              - 터무니없지만 않으면 돼요.
            </HatIntro>
          </Grid>
        </Grid>
      </Grid>
      <Grid item sm={12} md={1}/>
    </Grid>
  </div>
  );
};
