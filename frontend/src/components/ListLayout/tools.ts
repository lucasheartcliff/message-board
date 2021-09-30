import _ from 'lodash'
import { FilterOption } from '../../types'
import { contains } from '../../utils'

type AdvancedFilterType<T = any> = (
  data: T[],
  filterOptions: FilterOption[]
) => T[]

const getMatchesCount = (condition: boolean, matchesCount: number) =>
  condition ? matchesCount + 1 : matchesCount

export const advancedFilter: AdvancedFilterType = (data, filterOptions) => {
  let filteredData = []
  if (filterOptions.length) {
    for (const row of data) {
      let matchesCount = 0
      for (const { condition, field, term } of filterOptions) {
        const value = !isNaN(term as any) ? Number(term) : term.toUpperCase()

        let rowValue: any
        if (_.isArray(field)) {
          for (const path of field) {
            const value = _.get(row, path)

            if (value !== undefined) {
              rowValue = value
              break
            }
          }
        } else {
          rowValue = _.get(row, field)
        }

        if (rowValue === undefined) continue

        rowValue = !isNaN(rowValue as any)
          ? Number(rowValue)
          : rowValue.toUpperCase()

        switch (condition) {
          case '===':
            matchesCount = getMatchesCount(rowValue === value, matchesCount)
            break
          case '!==':
            matchesCount = getMatchesCount(rowValue !== value, matchesCount)
            break
          case '>':
            matchesCount = getMatchesCount(rowValue > value, matchesCount)
            break
          case '>=':
            matchesCount = getMatchesCount(rowValue >= value, matchesCount)
            break
          case '<':
            matchesCount = getMatchesCount(rowValue < value, matchesCount)
            break
          case '<=':
            matchesCount = getMatchesCount(rowValue <= value, matchesCount)
            break
          case 'like':
            matchesCount = getMatchesCount(
              contains(String(rowValue), String(value)),
              matchesCount
            )
            break
          case 'not like':
            matchesCount = getMatchesCount(
              !contains(String(rowValue), String(value)),
              matchesCount
            )
            break
        }
      }
      if (matchesCount === filterOptions.length) filteredData.push(row)
    }
    return filteredData
  } else {
    return data
  }
}
