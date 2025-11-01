import { add } from "./add.ts";
import { parseTemplate } from 'url-template';
const msg = "Hello from another script"
console.log(msg)
console.log(`add(2,3) = ${add(2, 3)}`);

const emailUrlTemplate = parseTemplate('/{email}/{folder}/{id}');
const emailUrl = emailUrlTemplate.expand({
  email: 'user@domain',
  folder: 'test',
  id: 42
});
console.log(emailUrl);