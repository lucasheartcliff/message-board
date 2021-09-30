import styled from 'styled-components';

export const ChatContainer = styled.div`
  display: flex;
  flex: 1;
  height: 250px;
  overflow-y: auto;
  flex-direction: column;

  .ant-page-header {
    padding: 10px 0;
    margin: 5px 15px;
    border-bottom: 1px solid #ccc;
  }
`;

export const EditorContainer = styled.div`
  padding: 0 10px;
`;
