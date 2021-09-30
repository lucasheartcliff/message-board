import styled from 'styled-components'

import * as G from '../../style/global'

export const Container = styled(G.Container)`
  padding: 10px 20px;
  align-items: flex-start;
  flex-direction: column;

  tr {
    cursor: pointer;
  }
`
export const Header = styled.div`
  display: flex;
  flex-direction: row;
  justify-content: space-between;
  width: 100%;
  margin-bottom: 10px;
`
export const TableContainer = styled.div`
  max-width: 100%;
  max-height: 100%;
`
export const ActionsContainer = styled.div`
  display: flex;
  width: 100%;
  justify-content: space-between;
  cursor: pointer;
`
