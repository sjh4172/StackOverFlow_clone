import LoginForm from '../components/Login/LoginForm';
import OAuthButton from '../components/Login/OAuthButton';
import EntireStyle from '../components/style/EntireStyle';
import { LoginStyle } from '../components/style/LoginStyle';

import ModalSideHeader from '../components/Header/Modal/ModalSideHeader';
import MainLogoContainer from '../components/Login/MainLogoContainer';

import GlobalSvgSprite from '../images/Sprite/GlobalSvgSprite';

/** 2023/03/17 - 로그인 페이지 - fe-hyungUk */
const Login = () => {
  return (
    <EntireStyle>
      <GlobalSvgSprite />

      <ModalSideHeader />
      <LoginStyle>
        <MainLogoContainer />
        <OAuthButton />
        <LoginForm />
      </LoginStyle>
    </EntireStyle>
  );
};

export default Login;
