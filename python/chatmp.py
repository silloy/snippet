# -*- coding: utf-8 -*-
"""
This module demonstrates documentation as specified by the `Google Python
Style Guide`_. Docstrings may extend over multiple lines. Sections are created
with a section header and a colon followed by a block of indented text.
"""

import itchatmp

itchatmp.update_config(itchatmp.WechatConfig(
    token='WeiXinToken',
    appId='wx1e24d19a5e54862d',
    appSecret='af687455ff78f497bc4e0ed26930512a'))

@itchatmp.msg_register(itchatmp.content.TEXT)
def text_reply(msg):
    """
        Example function with PEP 484 type annotations.
    Args:
        msg: The first parameter.
    Returns:
        The return value. True for success, False otherwise.
    """
    # text_reply
    return msg['content']

itchatmp.run()
