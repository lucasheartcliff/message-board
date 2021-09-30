import { SendOutlined } from '@ant-design/icons';
import { Button, Form } from 'antd';
import TextArea from 'antd/lib/input/TextArea';
import React from 'react';

interface Props {
  onChange: (value: string) => void;
  onSubmit: () => void;
  loading?: boolean;
  value?: string;
}

const Editor: React.FC<Props> = ({
  onChange,
  onSubmit,
  loading = false,
  value,
}) => (
  <>
    <Form.Item>
      <TextArea
        rows={4}
        style={{ resize: 'none' }}
        maxLength={1000}
        onChange={e => onChange(e.target.value)}
        value={value}
      />
    </Form.Item>
    <Form.Item>
      <Button
        icon={<SendOutlined />}
        htmlType="submit"
        loading={loading}
        onClick={onSubmit}
        type="primary"
      >
        Add Message
      </Button>
    </Form.Item>
  </>
);
export default Editor;
