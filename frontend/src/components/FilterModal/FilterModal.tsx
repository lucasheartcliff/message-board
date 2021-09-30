import Modal from 'antd/lib/modal/Modal'
import { ColumnsType } from 'antd/lib/table'
import React, { Dispatch, useCallback, useMemo, useState } from 'react'
import produce from 'immer'
import { Column, FilterOption } from '../../types'
import { Container, Footer, Row } from './styled'
import { Input, Select, Button } from 'antd'
import { filterConditions } from '../../utils'
import _ from 'lodash'

const { Option } = Select

interface Props<T = any> {
  columns: Column<T>[]
  visible: boolean
  options: FilterOption[]
  setOptions: Dispatch<any>
  hideModal: () => void
  onFilter: (options: FilterOption[]) => void
}

type FilterModalType<T = any> = React.FC<Props<T>>
const FilterModal: FilterModalType = ({
  columns,
  visible,
  options,
  onFilter,
  setOptions,
  hideModal,
}) => {
  const [conditionOptions, setConditionOptions] = useState<any>([])
  const [fieldsOptions, setFieldsOptions] = useState<any>([])

  const defaultRow = useMemo(
    () => ({
      field: columns[0].key as any,
      condition: filterConditions[0].value,
      term: '',
    }),
    [columns]
  )

  React.useEffect(() => {
    let columnOptions = []
    setConditionOptions(
      filterConditions.map(({ value, title }, index) => (
        <Option key={index} value={value} title={title} label={title}>
          {title}
        </Option>
      ))
    )

    for (const { filterKey, title, key } of columns) {
      if (filterKey) {
        columnOptions.push(
          <Option
            key={key}
            title={title as string}
            value={_.isArray(filterKey) ? JSON.stringify(filterKey) : filterKey}
            label={title}
          >
            {title}
          </Option>
        )
      }
    }
    setFieldsOptions(columnOptions)
  }, [])

  React.useEffect(() => {
    setOptions([defaultRow])
  }, [])

  const onChangeFilterValue = (
    filterKey: keyof FilterOption,
    value: any,
    index: number
  ) => {
    setOptions(
      produce((state: FilterOption[]) => {
        state[index][filterKey] = value
      })
    )
  }
  return (
    <Modal
      visible={visible}
      width={800}
      title={'Filtro Avançado'}
      onOk={() => onFilter(options)}
      onCancel={hideModal}
      footer={[
        <Footer>
          <Button
            key="addRow"
            type="primary"
            onClick={() =>
              setOptions(
                produce(state => {
                  state.push(defaultRow)
                })
              )
            }
          >
            {'Adicionar Linha'}
          </Button>
          <Button key="filter" type="primary" onClick={() => onFilter(options)}>
            {'Filtrar'}
          </Button>
        </Footer>,
      ]}
    >
      <Container>
        {options.map(({ condition, field, term }, index) => (
          <Row key={index}>
            <Select
              value={field as any}
              style={{ width: 270 }}
              optionLabelProp={'label'}
              children={fieldsOptions}
              onChange={value => onChangeFilterValue('field', value, index)}
            />
            <Select
              value={condition}
              style={{ width: 150 }}
              optionLabelProp={'label'}
              children={conditionOptions}
              onChange={value => onChangeFilterValue('condition', value, index)}
            />
            <Input
              value={term}
              style={{ width: 300 }}
              onChange={e => onChangeFilterValue('term', e.target.value, index)}
            />
          </Row>
        ))}
      </Container>
    </Modal>
  )
}

export default FilterModal
