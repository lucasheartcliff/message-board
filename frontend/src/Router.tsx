import React, { useContext } from 'react'
import BaseLayout from './components/BaseLayout/BaseLayout'
import { AuthContext } from './context/AuthContext'
import Routes, { LoginRoute } from './routes'
import { BrowserRouter } from 'react-router-dom'
import Loading from './components/Loading/Loading'

const Router = () => {
  const { isAuthenticated, loading } = useContext(AuthContext)
  return loading ? (
    <Loading spinning={loading} tip={'Carregando...'} />
  ) : (
    <BrowserRouter>
      {isAuthenticated ? (
        <BaseLayout>
          <Routes />
        </BaseLayout>
      ) : (
        <LoginRoute />
      )}
    </BrowserRouter>
  )
}
export default Router
