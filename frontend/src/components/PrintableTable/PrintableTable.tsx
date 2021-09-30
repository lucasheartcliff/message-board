import { Table } from 'antd'
import React from 'react'
import { forwardRef } from 'react'
import { Column } from '../../types'
import { Container, TableContainer, Title } from './styled'

interface Props {
  ref: any
  columns: Column<any>[]
  data: any[]
  title?: string
}

const PrintableTable = forwardRef<any, Props>(
  ({ data = [], columns, title }, ref) => {
    return (
      <Container ref={ref}>
        <Title>{title}</Title>
        <TableContainer>
          <Table
            columns={columns}
            dataSource={data}
            pagination={false}
            size={'small'}
          />
        </TableContainer>
      </Container>
    )
  }
)

export default React.memo(PrintableTable)
