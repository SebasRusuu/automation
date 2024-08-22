import type { SidebarsConfig } from '@docusaurus/plugin-content-docs';

const sidebars: SidebarsConfig = {
  docs: [
      'Objectives',
    {
      type: 'category',
      label: 'Tools for automation',
      items: [
        'Tools for automation/Tools'
      ],
    },
    {
      type: 'category',
      label: 'Install project',
      items: [
        'Run project/install-project',
        'Run project/run-configurations'
      ],
    },
    'How to run',
  ],
};

export default sidebars;