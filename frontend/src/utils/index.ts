import { message } from 'antd';
import { FilterCondition } from '../types';
import { Moment } from 'moment';
import moment from 'moment';
import 'moment/locale/pt-br';
import ReactDOM from 'react-dom';
import _ from 'lodash';

const jsonHeaders: RequestInit = {
    credentials: 'same-origin',
    headers: {
      'Content-Type': 'application/json'
    }
}


export const apiFetch = (url: string) => {
    const headers = jsonHeaders
    return {
        get: () =>
            fetch(url, {
                method: 'GET',
                ...headers
            }),
        post: (body: any) =>
            fetch(url, {
                method: 'POST',
                ...headers,
                body: JSON.stringify(body)
            }),
        patch: (body: any) =>
            fetch(url, {
                method: 'PATCH',
                ...headers,
                body: JSON.stringify(body)
            }),
        put: (body: any) =>
            fetch(url, {
                method: 'PUT',
                ...headers,
                body: JSON.stringify(body)
            }),
        delete: (body?: any) =>
            fetch(url, {
                method: 'DELETE',
                ...headers,
                body: body ? JSON.stringify(body) : undefined
            })
    }
}



interface IFilterConditions {
  value: FilterCondition;
  title: string;
}
export const filterConditions: IFilterConditions[] = [
  {
    value: '===',
    title: 'Igual',
  },
  {
    value: '!==',
    title: 'Diferente',
  },
  {
    value: '<',
    title: 'Maior',
  },
  {
    value: '<=',
    title: 'Maior ou Igual',
  },
  {
    value: '>',
    title: 'Menor',
  },
  {
    value: '>=',
    title: 'Menor ou Igual',
  },
  {
    value: 'like',
    title: 'Contém',
  },

  {
    value: 'not like',
    title: 'Não Contém',
  },
];

export const contains = (baseTerm: string, term: string) =>
  baseTerm.toUpperCase().includes(term.toUpperCase());

export const replaceNonNumericChararacters = (value?: string | number) => {
  return String(value).replace(/\D/g, '');
};

export const financialFormatter = (value?: string | number) => {
  return Number(value).toLocaleString('pt-BR', {
    style: 'currency',
    currency: 'BRL',
  });
};
export const financialParser = (value?: string) => {
  return value
    ? value
        .replace(CURRENCY_MONEY[0], '')
        .replace(CURRENCY_MONEY[1], '')
        .replace(/\./g, '')
        .replace(',', '.')
    : '0';
};

export const currencyFormatter = (value?: number | string) => {
  return new Intl.NumberFormat(LOCALE, {
    style: 'currency',
    currency: 'BRL',
  }).format(Number(value));
};

export const currencyParser = (val = '') => {
  // for when the input gets clears
  if (typeof val === 'string' && !val.length) {
    val = '0.0';
  }

  // detecting and parsing between comma and dot
  const group = new Intl.NumberFormat(LOCALE).format(1111).replace(/1/g, '');
  const decimal = new Intl.NumberFormat(LOCALE).format(1.1).replace(/1/g, '');

  let reversedVal = val.replace(new RegExp('\\' + group, 'g'), '');
  reversedVal = reversedVal.replace(new RegExp('\\' + decimal, 'g'), '.');

  // removing everything except the digits and dot
  reversedVal = reversedVal.replace(/[^0-9.]/g, '');

  // appending digits properly
  const digitsAfterDecimalCount = (reversedVal.split('.')[1] || []).length;

  const resultValue =  Number(reversedVal) * Math.pow(10, digitsAfterDecimalCount - 2);

  return Number.isNaN(resultValue) ? 0 : resultValue;
};

export const notification = {
  ...message,
};

export const getMoment = (date: string | Moment | Date) => {
  moment.locale(LOCALE);
  return moment(date);
};

export const dateFormater = (date: string | Moment | Date) =>
  getMoment(date).format(DATE_FORMAT);

export const dateTimeFormater = (date: string | Moment | Date) =>
  getMoment(date).format(DATE_TIME_FORMAT);

export const CURRENCY_MONEY = 'R$';
export const LOCALE = 'pt-br';
export const DATE_FORMAT = 'DD/MM/YYYY';
export const DATE_TIME_FORMAT = `${DATE_FORMAT} HH:mm`;

export const printComponent = (component: any, onPrint: Function) => {
  const div = document.getElementById('print') as HTMLElement;
  ReactDOM.render(component, div);
  onPrint();
  div.innerHTML = '';
};

export const sortBy = (a: any, b: any, attr?: string) => {
  const valueA = attr ? _.get(a, attr) : a;
  const valueB = attr ? _.get(b, attr) : b;

  if (_.isString(valueA) && _.isString(valueB)) {
    return valueA.localeCompare(valueB);
  } else if (_.isNumber(valueA) && _.isNumber(valueB)) {
    return valueA - valueB;
  } else if (moment.isMoment(valueA) && moment.isMoment(valueA)) {
    return valueA.valueOf() - valueB.valueOf();
  }
  return 0;
};
