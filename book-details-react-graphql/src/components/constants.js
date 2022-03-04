import { gql } from '@apollo/client';

export const QUERY_AUTHORS = gql`
{
  authors {
    id
    firstName
    lastName
  }
}
`;

export const QUERY_BOOKS = gql`
{
  books {
    id
    name
    pageCount
    author {
      id
      firstName
      lastName
    }
  }
}`;

export const ADD_BOOK = gql`
mutation PostMutation(
    $name: String!
    $pageCount: Int
    $authorId: ID
    $firstName: String
    $lastName: String
) {
    addBook(book:{
        name: $name
        pageCount:$pageCount
        author: {
          id: $authorId
          firstName: $firstName
          lastName: $lastName
  }}) {
      id
      name
      pageCount
      author {
        id
        firstName
        lastName
      }
    }
}`;