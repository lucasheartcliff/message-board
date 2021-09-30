import { ColumnType } from 'antd/lib/table';

export type FilterCondition =
  | '==='
  | '!=='
  | '>'
  | '<'
  | '>='
  | '<='
  | 'like'
  | 'not like';

export interface FilterOption {
  field: string | string[];
  condition: FilterCondition;
  term: string;
}

export interface Column<T = any> extends ColumnType<T> {
  filterKey?: string | string[];
}
