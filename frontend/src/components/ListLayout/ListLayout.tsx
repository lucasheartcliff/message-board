import {
  DeleteOutlined,
  EllipsisOutlined,
  PrinterOutlined,
} from '@ant-design/icons';
import { Button, Dropdown, Menu, Tooltip } from 'antd';
import Search from 'antd/lib/input/Search';
import Table from 'antd/lib/table';
import React, { useCallback, useEffect, useState, useRef } from 'react';
import { useReactToPrint } from 'react-to-print';
import { danger } from '../../style/color';
import { Column, FilterOption } from '../../types';
import FilterModal from '../FilterModal/FilterModal';
import PrintableTable from '../PrintableTable/PrintableTable';
import { ActionsContainer, Container, Header } from './styled';
import { advancedFilter } from './tools';

import { printComponent } from '../../utils';
import moment from 'moment';

interface Props<T> {
  columns: Column<T>[];
  data: T[];
  entityName?: string;
  loading?: boolean;
  onClickToCreate: (value: boolean) => void;
  onClickBasicFilterButton: (
    filterTerm: string,
    row: T,
    index: number
  ) => boolean;
  onClickRow?: (row: T, index: number) => void;
  onClickToDelete?: (row: T, index: number) => void;
}

type ListLayoutType<T = any> = React.FC<Props<T>>;
const ListLayout: ListLayoutType = ({
  data = [],
  columns,
  entityName,
  loading,
  onClickToCreate,
  onClickBasicFilterButton,
  onClickRow,
  onClickToDelete,
}) => {
  const printRef = useRef(null);
  const [filterTerm, setFilterTerm] = useState('');
  const [filterModalView, setFilterModalView] = useState(false);
  const [filteredData, setFilteredData] = useState<any>([]);
  const [filterOptions, setFilterOptions] = useState<FilterOption[]>([]);
  const printTableData = useReactToPrint({
    content: () => printRef.current,
  });

  useEffect(() => {
    setFilteredData(data);
  }, [data]);

  const filterHandler = () => {
    const filterValue = filterTerm.trim();
    const validOptions = filterOptions.filter(({ term }) => !!term.trim());
    let filteredValues = data;

    if (validOptions.length) {
      filteredValues = advancedFilter(data, validOptions);
    }

    if (filterValue) {
      filteredValues = filteredValues.filter((row, index) =>
        onClickBasicFilterButton(filterValue, row, index)
      );
      setFilteredData(filteredValues);
    }

    setFilteredData(filteredValues);
  };

  const onClickToPrint = () => {
    if (printTableData) {
      const component = (
        <PrintableTable
          ref={printRef}
          data={filteredData}
          columns={columns}
          title={
            entityName
              ? `${entityName} - ${moment().format('DD/MM/YYYY')}`
              : undefined
          }
        />
      );
      printComponent(component, printTableData);
    } else {
      console.error('Error on print method call');
    }
  };

  const menu = (
    <Menu>
      <Menu.Item
        key="print"
        icon={<PrinterOutlined />}
        onClick={onClickToPrint}
      >
        {'Imprimir'}
      </Menu.Item>
    </Menu>
  );

  return (
    <Container>
      <Header>
        <Search
          value={filterTerm}
          placeholder={'Pesquisar'}
          style={{ width: '80%' }}
          enterButton
          disabled={!!loading}
          loading={!!loading}
          onChange={e => setFilterTerm(e.target.value)}
          onSearch={filterHandler}
        />
        <div>
          <Button
            key="advancedFilter"
            type="primary"
            loading={!!loading}
            onClick={() => setFilterModalView(true)}
          >
            {'Filtro Avançado'}
          </Button>
          {/* <Dropdown overlay={menu} placement="bottomCenter">
            <Button
              key="tools"
              type="primary"
              icon={<EllipsisOutlined />}
              style={{ marginLeft: 10, width: 50 }}
            />
          </Dropdown> */}
        </div>
      </Header>
      <Table
        columns={
          onClickToDelete
            ? [
                ...columns,
                {
                  key: 'actions',
                  title: '',
                  width: 100,
                  fixed: 'right',
                  render: (_, row, index) => (
                    <ActionsContainer
                      onClick={e => {
                        e.stopPropagation();
                        onClickToDelete(row, index);
                      }}
                    >
                      <Tooltip placement="top" title={'Remover'}>
                        <DeleteOutlined style={{ color: danger }} />
                      </Tooltip>
                    </ActionsContainer>
                  ),
                },
              ]
            : columns
        }
        dataSource={filteredData}
        rowKey={'id'}
        loading={!!loading}
        pagination={filteredData.length}
        // pagination={{ pageSize: 10 }}
        size={'middle'}
        style={{ width: '100%', height: '90%' }}
        scroll={{ x: 'auto' }}
        onRow={(row, index) =>
          onClickRow
            ? {
                onClick: () => onClickRow(row, index as number),
              }
            : {}
        }
      />
      <FilterModal
        columns={columns}
        hideModal={() => setFilterModalView(false)}
        visible={filterModalView}
        options={filterOptions}
        setOptions={setFilterOptions}
        onFilter={filterHandler}
      />
    </Container>
  );
};

export default ListLayout;
