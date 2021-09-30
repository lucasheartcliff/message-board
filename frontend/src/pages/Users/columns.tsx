import React from 'react'
import { Column } from '../../types'
import {  User } from '../../types/entities'

export const columns: Column<User>[] = [
   {
    key: 'id',
    title: 'ID',
    dataIndex: 'id',
    filterKey: 'id',
  },
  {
    key: 'email',
    title: 'Email',
    dataIndex: 'email',
    filterKey: 'email',
  },
]
