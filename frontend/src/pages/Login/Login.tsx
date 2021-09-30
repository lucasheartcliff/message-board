import React from 'react'
import { Form, Input, Button, Card } from 'antd'

import * as S from './styled'
import { REQUIRED_FIELD } from '../../utils/message'
import { useContext } from 'react'
import { AuthContext, LoginPayload } from '../../context/AuthContext'
import { notification } from '../../utils'

const Login = () => {
  const [form] = Form.useForm<LoginPayload>()
  const { handleLogin } = useContext(AuthContext)

  const onSubmit = () => {
    form.validateFields().then(data => {
      const { email } = data
      handleLogin({ email: String(email).toLowerCase().trim() })
        .catch(err => {
          console.error(err)
          notification.error(err)
        })
    })
  }
  return (
    <S.Container>
      <Card title={'Login'}>
        <Form layout={'vertical'} form={form}>
          <Form.Item
            label="Email"
            name="email"
            rules={[{ required: true, message: REQUIRED_FIELD }]}
          >
            <Input type={'email'} />
          </Form.Item>
          <S.Footer>
            <Button
              key="submit"
              type="primary"
              htmlType={'submit'}
              onClick={onSubmit}
            >
              {'Login'}
            </Button>
          </S.Footer>
        </Form>
      </Card>
    </S.Container>
  )
}
export default Login
