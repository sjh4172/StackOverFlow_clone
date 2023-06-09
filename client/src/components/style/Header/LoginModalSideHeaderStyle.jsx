import styled from 'styled-components';
import { HeaderContainer, HeaderMenu, SearchInputWrapper, IconStyle, Icon2 as Icon1 } from './FixSideHeaderstyle';
import {
  HamburgerStyle,
  Icon1Open,
  Icon1Close,
  SidebarWrapper,
  SidebarMenu,
  SidebarCategories,
  InformationIcon,
  SidebarMenuItem,
  SideImg,
  SidebarStarImgWrapper,
} from './ModalSideHeaderStyle';

import { ProfileWrapper, NavIconStyle, Icon2, Icon3, Icon4, Icon5, Avatar, Num } from './UserProfile';

// 메인 로고
const Logo = styled.a`
  padding: 0 0.375rem 0 0.3rem;
`;

const LogoImg = styled.img`
  height: 9.9rem;
`;

const MenuButton = styled.a`
  text-decoration: none;
  font-size: 0.87rem;
  color: rgb(82, 82, 82);
  font-weight: 500;
  padding: 0.3125rem 0.625rem;
  margin: 0 0.375rem;
  cursor: pointer;

  &:hover {
    border-radius: 3.125rem / 3.125rem;
    color: rgb(0, 0, 0);
    background-color: rgba(227, 230, 232, 0.61);
  }
`;

// 돋보기 + 검색 인풋 : SearchInputWrapper
// 돋보기 아이콘 : IconStyle
// 검색 인풋
const SearchInput = styled.input`
  height: 1.375rem;
  width: 42rem;
  padding: 0.25rem 0.5rem;
  border: none;
  border-radius: 0.25rem;
  background-color: #fff;
  font-size: 0.8125rem;

  &:focus {
    outline: none;
  }
`;

// 모달
// SidebarWrapper, SidebarMenu, SidebarCategories, InformationIcon

// const ItemHover = styled.div`
//   border: 1px solid black;
//   &:hover {
//     background-color: rgb(241, 242, 243);
//     border-right: 0.1875rem solid rgb(244, 130, 37);
//   }
// `;

// 클릭됐을 때.
// &:hover {
//   color: black;
//   background-color: rgb(241, 242, 243);
//   border-right: 0.1875rem solid rgb(244, 130, 37);
// }

// SidebarMenuItem, SideImg, SidebarStarImgWrapper

export { HeaderContainer };
export { HamburgerStyle, Icon1Close, Icon1Open };
export { Logo, LogoImg };
export { HeaderMenu, MenuButton };
export { SearchInputWrapper, IconStyle, Icon1, SearchInput };
export { ProfileWrapper, Avatar, Num };
export { NavIconStyle, Icon2, Icon3, Icon4, Icon5 };
export { SidebarWrapper, SidebarMenu, SidebarCategories, InformationIcon };
export { SidebarMenuItem, SideImg, SidebarStarImgWrapper };
