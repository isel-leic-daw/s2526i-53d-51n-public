import { parseTemplate } from 'url-template';

const emailUrlTemplate = parseTemplate('/{email}/{folder}/{id}');
const emailUrl = emailUrlTemplate.expand({
  email: 'user@domain',
  folder: 'test',
  id: 42
});

console.log(emailUrl);