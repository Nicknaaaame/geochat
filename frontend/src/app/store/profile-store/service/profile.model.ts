import {Location} from '../../location-store/service/location.model'

export interface Profile {
  id: string,
  name: string,
  email: string,
  picture: string,
  showLocation: boolean,
  location: Location
}
