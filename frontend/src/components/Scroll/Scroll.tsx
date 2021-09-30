import React from 'react';
import Scrollbars, { ScrollbarProps } from 'react-custom-scrollbars';

interface Props extends ScrollbarProps {}

const Scroll = React.forwardRef<Scrollbars, Props>(
  ({ children, ...props }, ref) => {
    return (
      <Scrollbars ref={ref} {...props}>
        {children}
      </Scrollbars>
    );
  }
);

export default Scroll;
