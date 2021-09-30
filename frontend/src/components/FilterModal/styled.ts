import styled from 'styled-components'

export const Container = styled.div`
  display: flex;
  width: 100%;
  flex-direction: column;
`
export const Footer = styled.div`
  display: flex;
  width: 100%;
  flex-direction: row;
  justify-content: space-between;
`
export const Row = styled(Container)`
  flex-direction: row;
  justify-content: space-between;
  margin: 10px 0;
`
